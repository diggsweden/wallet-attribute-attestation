// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.infrastructure.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "attestations")
public class AttestationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "hsm_id")
  private UUID hsmId;
  @Column(name = "wua_id")
  private UUID wuaId;
  @Column(name = "attestation_data")
  private String attestationData;
  @Column(name = "created")
  private Instant created;

  public AttestationEntity() {
    this.created = Instant.now();
  }

  public AttestationEntity(UUID hsmId, UUID wuaId, String attestationData) {
    this.hsmId = hsmId;
    this.wuaId = wuaId;
    this.created = Instant.now();
    this.attestationData = attestationData;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getHsmId() {
    return hsmId;
  }

  public void setHsmId(UUID hsmId) {
    this.hsmId = hsmId;
  }

  public UUID getWuaId() {
    return wuaId;
  }

  public String getattestationData() {
    return attestationData;
  }

  public Instant getCreated() {
    return created;
  }

  public void setWuaId(UUID wuaId) {
    this.wuaId = wuaId;
  }

  public void setattestationData(String attestationData) {
    this.attestationData = attestationData;
  }

  public void setCreated(Instant timestamp) {
    this.created = timestamp;
  }

  @Transient
  public LocalDateTime getCreatedDateTime() {
    return LocalDateTime.ofInstant(created, ZoneId.systemDefault());
  }

  @Transient
  public void setCreatedDateTime(LocalDateTime localDateTime) {
    this.created = localDateTime.toInstant(ZoneOffset.ofHours(1));
  }



}
