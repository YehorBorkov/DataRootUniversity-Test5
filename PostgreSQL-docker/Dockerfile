FROM ubuntu:latest

# explicitly set user/group IDs
RUN groupadd -r postgres --gid=999 && useradd -r -g postgres --uid=999 postgres

# get required build tools
RUN apt-get update && apt-get install -y make gcc

# make the "en_US.UTF-8" locale so postgres will be utf-8 enabled by default
RUN apt-get update && apt-get install -y locales && localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.utf8

# create build dir
RUN mkdir -p /postgres-build
WORKDIR /postgres-build

# get and unpack PostgreSQL source code
ADD https://ftp.postgresql.org/pub/source/v10.1/postgresql-10.1.tar.gz .
RUN tar -xf postgresql-10.1.tar.gz
WORKDIR postgresql-10.1

# configure and make PostgreSQL
RUN ./configure --without-readline --without-zlib && make && make install

# remove unnecessary build tools and temporary dirs
WORKDIR /
RUN apt-get purge -y --auto-remove make gcc && rm -rf /var/lib/apt/lists/* && rm -rf /var/cache/* && rm -rf /postgres-build

# create and update environment
ENV PGHOME "/usr/local/pgsql"
ENV PGDATA "/usr/local/pgsql/data"
ENV PATH "/usr/local/pgsql/bin:$PATH"

# create the postgres data directory and make postgres user as the owner.
RUN mkdir -p "$PGDATA" && chown -R postgres:postgres "$PGDATA" && chmod 777 "$PGDATA"

# initialize and configure database
USER postgres:postgres
RUN initdb -D "$PGDATA"
RUN sed -ri "s!^#?(listen_addresses)\s*=\s*\S+.*!\1 = '*'!" "$PGDATA/postgresql.conf" \
	&& echo "host    all             all              all                    trust" >> "$PGDATA/pg_hba.conf"

# run database
ENTRYPOINT ["postgres"]
EXPOSE 5432
CMD ["-D", "/usr/local/pgsql/data"]
