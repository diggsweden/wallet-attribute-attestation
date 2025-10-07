// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.infrastructure.repostitory;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import se.digg.wallet.attributeattestation.SharedPostgresContainer;
import se.digg.wallet.attributeattestation.domain.model.AttestationDto;
import se.digg.wallet.attributeattestation.infrastructure.model.AttestationEntity;

@DataJpaTest
@Testcontainers
class AttestationRepositoryTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = SharedPostgresContainer.getContainer();

  @Autowired
  AttestationRepository repository;

  @Autowired
  TestEntityManager entityManager;

  @Test
  void saveAndRetrieveAttestation() {
    UUID hsmID = UUID.randomUUID();
    UUID wuaID = UUID.randomUUID();
    String jwtExample = """
        eyJraWQiOiIxIiwidHlwIjoia2V5YXR0ZXN0YXRpb24rand0IiwiYWxnIjoiRVMyNTYifQ\
        .eyJpc3MiOiJEaWdnIiwiYXR0ZXN0ZWRfa2V5cyI6W3sia3R5IjoiRUMiLCJjcnYiOiJQL\
        TI1NiIsIngiOiIxZkgwZXFYZ01Nd0NJYWZOYURjMWF4ZENqTGx3N3pwVEx2TFdqcFB2aEV\
        jIiwieSI6IjVxT2VqSnM3QkstakxpbmdhVVRFaEJyelBfWVB5SGZwdFM1eVdFOThJNDAifV\
        0sImV4cCI6MTc1OTkxNTA0OCwiaWF0IjoxNzU5ODI4NjQ4LCJldWRpX3dhbGxldF9pbmZvI\
        jp7IndzY2RfaW5mbyI6eyJ3c2NkX2NlcnRpZmljYXRpb25faW5mb3JtYXRpb24iOiJVTkNF\
        UlRJRklFRCJ9LCJnZW5lcmFsX2luZm8iOnsid2FsbGV0X3Byb3ZpZGVyX25hbWUiOiJEaWd\
        nIiwid2FsbGV0X3NvbHV0aW9uX2lkIjoiRGlnZ2lkaWdnLWlkIiwid2FsbGV0X3NvbHV0aW\
        9uX2NlcnRpZmljYXRpb25faW5mb3JtYXRpb24iOiJVTkNFUlRJRklFRCIsIndhbGxldF9zb\
        2x1dGlvbl92ZXJzaW9uIjoiMC4wLjEifX0sInN0YXR1cyI6eyJzdGF0dXNfbGlzdCI6eyJ1\
        cmkiOiJodHRwczovL3Jldm9jYXRpb25fdXJsL3N0YXR1c2xpc3RzLzEiLCJpZHgiOjQxMn1\
        9fQ.E4llp2KfY1wOzhmfvdSQ83Q8e_SjOUGaPIBg2-XdF-rWQbRBsvi67ZGeOldkJ_QG-Vn\
        BrTg0Vf-TGCGNSx3wq
        """;

    AttestationEntity originalEntity = new AttestationEntity(hsmID, wuaID, jwtExample);

    repository.save(originalEntity);

    entityManager.flush();
    entityManager.clear();

    AttestationEntity foundEntity = repository.findById(originalEntity.getId()).get();

    assertEquals(originalEntity.getId(), foundEntity.getId());
    assertEquals(originalEntity.getHsmId(), foundEntity.getHsmId());
    assertEquals(originalEntity.getWuaId(), foundEntity.getWuaId());
    assertNotNull(originalEntity.getCreated()); // dummy test as I do not mock the clock
    assertNotNull(originalEntity.getCreatedDateTime());
    assertEquals(jwtExample, originalEntity.getattestationData());
  }

  @Test
  void testFindall() {
    UUID hsmID = UUID.randomUUID();
    UUID wuaID = UUID.randomUUID();
    int i;
    for(i = 0; i<2; i++) {
      AttestationEntity entity = new AttestationEntity(hsmID, wuaID, String.valueOf(i));
      repository.save(entity);
    }
    entityManager.flush();
    entityManager.clear();
    List<AttestationDto> dtos =  repository.findAllByHsmId(hsmID);
    assertThat(dtos.size()).isEqualTo(2);

  }
}
