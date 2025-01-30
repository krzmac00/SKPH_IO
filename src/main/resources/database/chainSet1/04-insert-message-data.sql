--liquibase formatted sql
--changeset userName:2
-- Insert users before inserting messages
INSERT INTO users (firstName, lastName, username, password, role, email, organization)
VALUES ('John', 'Doe', 'user1', 'password1', 'user', 'user1@example.com', 'Organization1');

INSERT INTO users (firstName, lastName, username, password, role, email, organization)
VALUES ('Jane', 'Smith', 'user2', 'password2', 'admin', 'user2@example.com', 'Organization2');

-- Now insert messages
INSERT INTO message (content, messageType, senderId, recipientId)
VALUES ('Testowa wiadomość 1', 'TEXT', 1, 2),
       ('Testowa wiadomość 2', 'TEXT', 2, 1);

INSERT INTO message (content, messageType, senderId, recipientId)
VALUES
    ('Testowa wiadomość 1', 'TEXT', 1, 2),
    ('Testowa wiadomość 2', 'TEXT', 2, 1);