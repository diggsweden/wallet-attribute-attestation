// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.infrastructure.mapper;

import java.time.Clock;
import java.time.Instant;
import org.springframework.stereotype.Service;
import se.digg.wallet.attributeattestation.application.model.CreateAttestationRequestDto;
import se.digg.wallet.attributeattestation.domain.model.AttestationDto;
import se.digg.wallet.attributeattestation.domain.model.AttestationDtoBuilder;
import se.digg.wallet.attributeattestation.infrastructure.model.AttestationEntity;

@Service
public final class AttestationEntityMapper {

  private Clock clock;

  public AttestationEntityMapper(Clock clock) {
    this.clock = clock;
  }

  public AttestationDto toDomain(AttestationEntity entity) {
    return AttestationDtoBuilder.builder()
        .id(entity.getId())
        .hsmId(entity.getHsmId())
        .wuaId(entity.getWuaId())
        .attestationData(entity.getattestationData())
        .build();
  }

  public AttestationEntity toEntity(CreateAttestationRequestDto attestationRequest) {
    return new AttestationEntity(attestationRequest.hsmId(), attestationRequest.wuaId(),
        attestationRequest.attestationData(), Instant.now(clock));
  }
}
