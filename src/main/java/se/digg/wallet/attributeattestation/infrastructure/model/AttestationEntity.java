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
import java.time.Instant;
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

  public AttestationEntity() {}

  public AttestationEntity(UUID hsmId, UUID wuaId, String attestationData, Instant created) {
    this.hsmId = hsmId;
    this.wuaId = wuaId;
    this.created = created;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((hsmId == null) ? 0 : hsmId.hashCode());
    result = prime * result + ((wuaId == null) ? 0 : wuaId.hashCode());
    result = prime * result + ((attestationData == null) ? 0 : attestationData.hashCode());
    result = prime * result + ((created == null) ? 0 : created.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    AttestationEntity other = (AttestationEntity) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (hsmId == null) {
      if (other.hsmId != null) {
        return false;
      }
    } else if (!hsmId.equals(other.hsmId)) {
      return false;
    }
    if (wuaId == null) {
      if (other.wuaId != null) {
        return false;
      }
    } else if (!wuaId.equals(other.wuaId)) {
      return false;
    }
    if (attestationData == null) {
      if (other.attestationData != null) {
        return false;
      }
    } else if (!attestationData.equals(other.attestationData)) {
      return false;
    }
    if (created == null) {
      if (other.created != null) {
        return false;
      }
    } else if (!created.equals(other.created)) {
      return false;
    }
    return true;
  }
}
