
INSERT INTO acl_sid (id, principal, sid) VALUES
  (1, FALSE, 'ROLE_ADMIN'),
  (2, FALSE, 'ROLE_USER'),
  (3, FALSE, 'ROLE_ADVANCED_USER');

INSERT INTO acl_class (id, class) VALUES
  (1, 'ru.otus.dz21.domain.Book');

INSERT INTO acl_object_identity
  (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
  VALUES
  (1, 1, 1, NULL, 1, FALSE );

INSERT INTO acl_entry
  (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
  VALUES
  (1, 1, 1, 2, 0, TRUE , TRUE , TRUE ),
  (2, 1, 2, 3, 1, TRUE , TRUE , TRUE ),
  (3, 1, 3, 3, 2, TRUE , TRUE , TRUE );