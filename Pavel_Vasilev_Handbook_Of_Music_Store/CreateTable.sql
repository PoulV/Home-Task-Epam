DROP TABLE musicians, albums, compositions, musician_albums, album_songs;

CREATE TABLE musicians (
  musician_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE albums (
  album_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  genre VARCHAR(50) NOT NULL
);

CREATE TABLE compositions (
  composition_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  length BIGINT CONSTRAINT positive_length CHECK (length > 0)  
);

CREATE TABLE musician_albums (
  musician_id INT REFERENCES musicians(musician_id),
  album_id INT REFERENCES albums(album_id)
);

CREATE TABLE album_songs (  
  album_id INT REFERENCES albums(album_id),
  composition_id INT REFERENCES compositions(composition_id)
);
