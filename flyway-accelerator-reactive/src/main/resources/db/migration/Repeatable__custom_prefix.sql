/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

-- Here we can define repeatable SQL which should be called on each migration.
-- E.g. triggers or views creation, default schema selection etc.
select count(*)
from users;