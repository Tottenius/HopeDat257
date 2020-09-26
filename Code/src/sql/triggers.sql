CREATE FUNCTION registerNewUserFuction()
    RETURNS TRIGGER AS $$
BEGIN
    IF (NEW.id <= (SELECT MAX(id) FROM users))
        THEN
            NEW.id := (SELECT MAX(id)FROM users)+1;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER  registerNewUser
    BEFORE INSERT ON users
    FOR EACH ROW
EXECUTE PROCEDURE registerNewUserFuction();