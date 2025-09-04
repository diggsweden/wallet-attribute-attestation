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

  public AttestationEntity() {}

  public AttestationEntity(UUID hsmId, UUID wuaId) {
    this.hsmId = hsmId;
    this.wuaId = wuaId;
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

  public void setWuaId(UUID wuaId) {
    this.wuaId = wuaId;
  }
}
