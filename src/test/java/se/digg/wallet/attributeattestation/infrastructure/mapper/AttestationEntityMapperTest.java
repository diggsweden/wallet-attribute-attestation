// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.infrastructure.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import se.digg.wallet.attributeattestation.application.model.CreateAttestationRequest;
import se.digg.wallet.attributeattestation.domain.model.Attestation;
import se.digg.wallet.attributeattestation.infrastructure.model.AttestationEntity;

class AttestationEntityMapperTest {

  @Test
  void toDomain() {
    AttestationEntity entity = new AttestationEntity();
    entity.setId(UUID.randomUUID());
    entity.setHsmId(UUID.randomUUID());
    entity.setWuaId(UUID.randomUUID());

    Attestation domain = AttestationEntityMapper.toDomain(entity);

    assertThat(domain)
        .usingRecursiveComparison()
        .comparingOnlyFields("id", "hsmId", "wuaId")
        .isEqualTo(entity);
  }


  @Test
  void toEntity() {
    CreateAttestationRequest request =
        new CreateAttestationRequest(UUID.randomUUID(), UUID.randomUUID());

    AttestationEntity entity = AttestationEntityMapper.toEntity(request);

    assertThat(entity)
        .usingRecursiveComparison()
        .comparingOnlyFields("hsmId", "wuaId")
        .isEqualTo(request);
  }

}
