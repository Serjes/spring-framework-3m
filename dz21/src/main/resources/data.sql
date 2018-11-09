-- insert into AUTHORS (id, first_name, last_name) values (1, 'Борис', 'Акунин');
-- insert into AUTHORS (id, first_name, last_name) values (2, 'Лев', 'Толстой');
-- insert into AUTHORS (id, first_name, last_name) values (3, 'Мария', 'Миронова');
--
-- insert into GENRES (id, name) values (1, 'детектив');
-- insert into GENRES (id, name) values (2, 'роман');
-- insert into GENRES (id, name) values (3, 'медицина');
--
-- insert into BOOKS (id, title, author_id, genre_id) values (1, 'Азазель', 1, 1);
-- insert into BOOKS (id, title, author_id, genre_id) values (2, 'Война и мир', 2, 2);
-- insert into BOOKS (id, title, author_id, genre_id) values (3, 'Анна Каренина', 2, 2);
-- insert into BOOKS (id, title, author_id, genre_id) values (4, 'Стоматологические заболевания. Учебник', 3, 3);

INSERT INTO acl_sid (id, principal, sid) VALUES
  (1, FALSE, 'ROLE_ADMIN'),
  (2, FALSE, 'ROLE_USER'),
  (3, FALSE, 'ROLE_ADVANCED_USER');

INSERT INTO acl_class (id, class) VALUES
  (1, 'ru.otus.dz21.domain.Book');

INSERT INTO acl_object_identity
  (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
  VALUES
  (1, 1, 1, NULL, 1, FALSE ),
  (2, 1, 2, NULL, 1, FALSE ),
  (3, 1, 3, NULL, 1, FALSE ),
  (4, 1, 4, NULL, 1, FALSE );

INSERT INTO acl_entry
  (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
  VALUES
  (1, 1, 1, 2, 1, TRUE , TRUE , TRUE ),
  (2, 1, 2, 3, 1, TRUE , TRUE , TRUE ),
  (3, 2, 1, 2, 1, TRUE , TRUE , TRUE ),
  (4, 2, 2, 3, 1, TRUE , TRUE , TRUE ),
  (5, 3, 1, 2, 1, TRUE , TRUE , TRUE ),
  (6, 3, 2, 3, 1, TRUE , TRUE , TRUE ),
  (7, 4, 1, 2, 0, TRUE , TRUE , TRUE ),
  (8, 4, 2, 3, 1, TRUE , TRUE , TRUE ),
  (9, 4, 3, 3, 2, TRUE , TRUE , TRUE );