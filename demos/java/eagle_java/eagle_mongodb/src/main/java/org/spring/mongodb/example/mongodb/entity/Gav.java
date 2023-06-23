package org.spring.mongodb.example.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Gav")
@TypeAlias("Gav")
public class Gav {
    @Id
    private String uid;
    private String groupId;
    private String artifactId;
    private String version;
    private String language;
    private Gav parent;
    private List<Gav> dependencies;
    private List<Gav> scopeImports;
}
