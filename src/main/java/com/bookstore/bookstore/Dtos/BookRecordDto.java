package com.bookstore.bookstore.Dtos;

import java.util.Set;
import java.util.UUID;

public record BookRecordDto(String title,
                            UUID IdPublisher,
                            Set<UUID> IdsAuthor,
                            String reviewComment) {
}
