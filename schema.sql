CREATE TABLE users (
    user_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    role TEXT NOT NULL CHECK (role IN ('user', 'verifier'))
);

CREATE TABLE rumours (
    rumour_id INTEGER PRIMARY KEY CHECK (
        rumour_id BETWEEN 10000000 AND 99999999
    ),
    title TEXT NOT NULL,
    source TEXT NOT NULL,
    created_at TEXT NOT NULL,
    credibility_score INTEGER DEFAULT 0,
    status TEXT NOT NULL CHECK (status IN ('normal', 'panic')),
    is_verified INTEGER DEFAULT 0
);

CREATE TABLE reports (
    report_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    rumour_id INTEGER NOT NULL,
    report_type TEXT NOT NULL CHECK (
        report_type IN ('distortion', 'incitement', 'fake')
    ),
    reported_at TEXT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (rumour_id) REFERENCES rumours(rumour_id),

    UNIQUE (user_id, rumour_id)
);

CREATE TABLE verifications (
    rumour_id INTEGER PRIMARY KEY,
    verifier_id INTEGER NOT NULL,
    result TEXT NOT NULL CHECK (result IN ('true', 'false')),
    verified_at TEXT NOT NULL,

    FOREIGN KEY (rumour_id) REFERENCES rumours(rumour_id),
    FOREIGN KEY (verifier_id) REFERENCES users(user_id)
);

