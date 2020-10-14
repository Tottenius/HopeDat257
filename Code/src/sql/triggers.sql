CREATE FUNCTION addEmissionFunction()
     RETURNS TRIGGER AS $$
 BEGIN
     IF (NEW.id <= (SELECT MAX(id) FROM EmissionData WHERE username = NEW.username AND date = NEW.date AND food = NEW.food))
         THEN
             NEW.id := (SELECT MAX(id) FROM EmissionData WHERE username = NEW.username AND date = NEW.date AND food = NEW.food)+1;
     END IF;
     RETURN NEW;
 END;
 $$ LANGUAGE 'plpgsql';

CREATE TRIGGER  addEmission
    BEFORE INSERT ON EmissionData
    FOR EACH ROW
EXECUTE PROCEDURE addEmissionFunction();


CREATE FUNCTION changePasswordFuction()
    RETURNS TRIGGER AS $$
BEGIN
    IF (NEW.password IS NULL OR NEW.password = '')
    THEN
        NEW.password = OLD.password;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER  changePassword
    BEFORE UPDATE ON users
    FOR EACH ROW
EXECUTE PROCEDURE changePasswordFuction();