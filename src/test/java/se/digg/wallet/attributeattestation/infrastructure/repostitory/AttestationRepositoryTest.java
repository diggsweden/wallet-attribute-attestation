// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.infrastructure.repostitory;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    AttestationEntity originalEntity = new AttestationEntity(hsmID, wuaID);

    repository.save(originalEntity);

    entityManager.flush();
    entityManager.clear();

    AttestationEntity foundEntity = repository.findById(originalEntity.getId()).get();

    assertEquals(originalEntity.getId(), foundEntity.getId());
    assertEquals(originalEntity.getHsmId(), foundEntity.getHsmId());
    assertEquals(originalEntity.getWuaId(), foundEntity.getWuaId());
  }
}
