INSERT INTO acl_sid (id, principal, sid) VALUES
  (1, 0, 'ROLE_LIBRARIAN'),
  (2, 0, 'ROLE_HUMOR'),
  (3, 0, 'ROLE_DETECTIVE');

INSERT INTO acl_class (id, class) VALUES
  (1, 'ru.otus.dz21.domain.Genre');

INSERT INTO acl_object_identity
  (id, object_id_class, object_id_identity,
  parent_object, owner_sid, entries_inheriting)
  VALUES
  (1, 1, 1, NULL, 1, 0),
  (2, 1, 2, NULL, 1, 0);

INSERT INTO acl_entry
  (id, acl_object_identity, ace_order,
  sid, mask, granting, audit_success, audit_failure)
  VALUES
  (1, 1, 1, 2, 1, 1, 1, 1),
  (2, 2, 1, 3, 1, 1, 1, 1);