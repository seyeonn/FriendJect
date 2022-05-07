package com.ssafy.db.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmptyDTO {
	 private boolean isEmpty;

    public EmptyDTO(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
