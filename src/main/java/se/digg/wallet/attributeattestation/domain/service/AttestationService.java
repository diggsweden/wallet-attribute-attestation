// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import se.digg.wallet.attributeattestation.application.model.CreateAttestationRequestDto;
import se.digg.wallet.attributeattestation.domain.model.AttestationDto;
import se.digg.wallet.attributeattestation.infrastructure.mapper.AttestationEntityMapper;
import se.digg.wallet.attributeattestation.infrastructure.model.AttestationEntity;
import se.digg.wallet.attributeattestation.infrastructure.repostitory.AttestationRepository;

@Component
public class AttestationService {

  private final AttestationRepository attestationRepository;

  public AttestationService(AttestationRepository attestationRepository) {
    this.attestationRepository = attestationRepository;
  }

  public Optional<AttestationDto> getAttestationById(UUID uuid) {
    return attestationRepository.findById(uuid)
        .map(AttestationEntityMapper::toDomain);
  }

  public List<AttestationDto> getAttestationsByHsmId(UUID uuid) {
    return attestationRepository.findAllByHsmId(uuid);
  }

  public AttestationDto createAttestation(CreateAttestationRequestDto attestationRequestDto) {
    AttestationEntity save =
        attestationRepository.save(AttestationEntityMapper.toEntity(attestationRequestDto));
    return AttestationEntityMapper.toDomain(save);
  }
}
