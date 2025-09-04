// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import se.digg.wallet.attributeattestation.application.model.CreateAttestationRequest;
import se.digg.wallet.attributeattestation.domain.model.Attestation;
import se.digg.wallet.attributeattestation.infrastructure.model.AttestationEntity;
import se.digg.wallet.attributeattestation.infrastructure.repostitory.AttestationRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class AttestationIT {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = SharedPostgresContainer.getContainer();

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  AttestationRepository repository;

  @Test
  @WithMockUser
  void getAttestation() {
    UUID hsmID = UUID.randomUUID();
    UUID wuaID = UUID.randomUUID();
    AttestationEntity entity = new AttestationEntity(hsmID, wuaID);

    repository.save(entity);

    ResponseEntity<Attestation> response =
        restTemplate.getForEntity("/attestation/" + entity.getId(), Attestation.class);

    assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

    assertThat(response.getBody())
        .isNotNull()
        .satisfies(attestation -> {
          assertThat(attestation.hsmId()).isEqualTo(hsmID);
          assertThat(attestation.wuaId()).isEqualTo(wuaID);
          assertThat(attestation.id()).isEqualTo(entity.getId());
        });
  }


  @Test
  @WithMockUser
  void createAttestation() {
    UUID hsmID = UUID.randomUUID();
    UUID wuaID = UUID.randomUUID();
    CreateAttestationRequest request = new CreateAttestationRequest(hsmID, wuaID);

    ResponseEntity<Attestation> response =
        restTemplate.postForEntity("/attestation", request, Attestation.class);

    assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

    assertThat(response.getBody())
        .isNotNull()
        .satisfies(attestation -> {
          assertThat(attestation.hsmId()).isEqualTo(hsmID);
          assertThat(attestation.wuaId()).isEqualTo(wuaID);
          assertThat(attestation.id()).isNotNull();
        });
  }
}
