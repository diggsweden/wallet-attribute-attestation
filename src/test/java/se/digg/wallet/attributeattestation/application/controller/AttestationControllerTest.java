// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.application.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.digg.wallet.attributeattestation.domain.model.Attestation;
import se.digg.wallet.attributeattestation.domain.service.AttestationService;

@WebMvcTest(AttestationController.class)
@WithMockUser
class AttestationControllerTest {

  @Autowired
  MockMvc mvc;

  @MockitoBean
  AttestationService attestationService;

  @Test
  void notFound_returned_for_missing_attestation() throws Exception {
    UUID uuid = UUID.randomUUID();
    when(attestationService.getAttestationById(uuid)).thenReturn(Optional.empty());

    String path = String.format("/attestation/%s", uuid);

    mvc.perform(get(path)).andExpect(status().isNotFound());
  }

  @Test
  void badRequest_returned_for_invalid_uuid() throws Exception {
    String path = String.format("/attestation/%s", "not a uuid");
    mvc.perform(get(path)).andExpect(status().isBadRequest());
  }

  @Test
  void internalServerError_returned_for_exception() throws Exception {
    UUID uuid = UUID.randomUUID();
    when(attestationService.getAttestationById(uuid)).thenThrow(new RuntimeException("Some error"));
    String path = String.format("/attestation/%s", uuid);
    mvc.perform(get(path)).andExpect(status().isInternalServerError());
  }

  @Test
  void attestation_returned_for_existing_attestation() throws Exception {
    UUID uuid = UUID.randomUUID();
    when(attestationService.getAttestationById(uuid)).thenReturn(
        Optional.of(new Attestation(uuid, UUID.randomUUID(), UUID.randomUUID())));
    String path = String.format("/attestation/%s", uuid);

    mvc.perform(get(path)).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(uuid.toString()))
        .andExpect(content().contentType("application/json"));
  }

}
