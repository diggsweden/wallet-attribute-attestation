// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.infrastructure.repostitory;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import se.digg.wallet.attributeattestation.infrastructure.model.AttestationEntity;

public interface AttestationRepository extends CrudRepository<AttestationEntity, UUID> {
}
