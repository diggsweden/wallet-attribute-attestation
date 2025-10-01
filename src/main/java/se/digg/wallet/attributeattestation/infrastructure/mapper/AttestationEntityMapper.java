// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.infrastructure.mapper;

import se.digg.wallet.attributeattestation.application.model.CreateAttestationRequestDto;
import se.digg.wallet.attributeattestation.domain.model.AttestationDto;
import se.digg.wallet.attributeattestation.domain.model.AttestationDtoBuilder;
import se.digg.wallet.attributeattestation.infrastructure.model.AttestationEntity;

public final class AttestationEntityMapper {

  private AttestationEntityMapper() {}

  public static AttestationDto toDomain(AttestationEntity entity) {
    return AttestationDtoBuilder.builder()
        .id(entity.getId())
        .hsmId(entity.getHsmId())
        .wuaId(entity.getWuaId())
        .build();
  }

  public static AttestationEntity toEntity(CreateAttestationRequestDto attestationRequest) {
    return new AttestationEntity(attestationRequest.hsmId(), attestationRequest.wuaId());
  }
}
