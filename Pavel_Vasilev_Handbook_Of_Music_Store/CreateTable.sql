CREATE TABLE IF NOT EXISTS musicians (
  musician_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS albums (
  album_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  genre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS compositions (
  composition_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  length BIGINT CONSTRAINT positive_length CHECK (length > 0)  
);

CREATE TABLE IF NOT EXISTS musician_albums (
  musician_id INT REFERENCES musicians(musician_id),
  album_id INT REFERENCES albums(album_id),
  UNIQUE (musician_id, album_id)
);

CREATE TABLE IF NOT EXISTS album_songs (
  album_id INT REFERENCES albums(album_id),
  composition_id INT REFERENCES compositions(composition_id),
  UNIQUE (album_id, composition_id)
);
