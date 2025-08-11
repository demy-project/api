# Java Annotation Usage and Ordering Guidelines (JPA + Lombok)

This document defines a standard for the usage and ordering of annotations in Java classes within our codebase. The goal is to ensure consistency, readability, and maintainability across the team.

---

## Recommended Annotation Order

When a class or field uses multiple annotations, the order of those annotations affects readability and maintainability, even if it doesn't impact runtime behavior in most cases.

### Suggested Order:

1. **Persistence / JPA annotations**  
   Examples: @Entity, @Table, @Id, @Column, @Embedded, @OneToMany, etc.

2. **Validation / Serialization annotations (if applicable)**  
   Examples: @NotNull, @Size, @JsonProperty, @JsonIgnore, etc.

3. **Utility / Lombok annotations**  
   Examples: @Getter, @Setter, @Builder, @ToString, etc.

---

## Examples

### Class with JPA and Lombok annotations

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "academies")
@Getter
@NoArgsConstructor
public class Academy {
//...
}
```

### Field with multiple annotations

```java
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Embedded
@NotNull
@Getter
private AdministratorId administratorId;
```

---

## Additional Guidelines

- Group annotations in the recommended order and align them vertically for better readability.
- Prefer class-level @Getter if all fields are to be exposed, to avoid repetition.
- Avoid using Lombok's @Data unless all its generated methods (equals, hashCode, toString, getters/setters) are explicitly needed. Prefer more granular annotations.
- For JPA entities, provide a no-argument constructor with protected access. You can use @NoArgsConstructor(access = AccessLevel.PROTECTED).
- Do not mix business logic and persistence logic within the same class.

---

## References

- [Lombok Documentation](https://projectlombok.org/features/all)
- [Jakarta Persistence Specification](https://jakarta.ee/specifications/persistence/)
- [Clean Code by Robert C. Martin](https://www.oreilly.com/library/view/clean-code/9780136083238/)

---

## Team Usage

All team members must follow this guideline when:

- Creating new JPA entities.
- Adding annotations to existing classes or fields.
- Reviewing code in Pull Requests.

Consistent annotation usage improves code quality and reduces misunderstandings during development and code reviews.
