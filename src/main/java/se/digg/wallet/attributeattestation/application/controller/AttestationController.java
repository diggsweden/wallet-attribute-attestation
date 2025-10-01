// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.application.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.digg.wallet.attributeattestation.application.model.CreateAttestationRequestDto;
import se.digg.wallet.attributeattestation.domain.model.AttestationDto;
import se.digg.wallet.attributeattestation.domain.service.AttestationService;


@RestController
@RequestMapping("/attestation")
public class AttestationController {

  private final AttestationService attestationService;

  public AttestationController(AttestationService attestationService) {
    this.attestationService = attestationService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<AttestationDto> getAttestation(@PathVariable final UUID id) {
    return attestationService.getAttestationById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping()
  public ResponseEntity<AttestationDto> saveAttestation(
      @Valid @RequestBody CreateAttestationRequestDto attestationRequest) {
    return ResponseEntity.ok(attestationService.createAttestation(attestationRequest));
  }
}
