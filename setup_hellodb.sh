#!/bin/bash
dropdb hellodb
psql -c "DROP USER hello"
createdb hellodb
psql -f ${0/sh/sql}
