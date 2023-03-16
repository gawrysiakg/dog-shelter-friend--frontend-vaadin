package com.example.application.data.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

    @MappedSuperclass
    public abstract class AbstractEntity implements Serializable {
        //work in progress
        @Id
        private Long id;
        @Version
        private int version;

        public Long getId() {
            return id;
        }

        public int getVersion() {
            return version;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, version);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            AbstractEntity that = (AbstractEntity) o;
            return version == that.version &&
                    Objects.equals(id, that.id);
        }
    }

