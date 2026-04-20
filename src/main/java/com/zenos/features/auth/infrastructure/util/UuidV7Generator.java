package com.core.auth.infrastructure.util;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class UuidV7Generator {

    public static UUID generate() {
        return Generators.timeBasedEpochGenerator().generate();
    }
}
