INSERT INTO users (user_id, name, role) VALUES
(1, 'Alice', 'user'),
(2, 'Bob', 'user'),
(3, 'Charlie', 'user'),
(4, 'David', 'user'),
(5, 'Eva', 'user'),
(6, 'Frank', 'user'),
(7, 'Grace', 'user'),
(8, 'Helen', 'user'),
(9, 'Ivan', 'verifier'),
(10, 'Judy', 'verifier');


INSERT INTO rumours
(rumour_id, title, source, created_at, credibility_score, status, is_verified)
VALUES
(12345678, 'Water shortage next week', 'Facebook', '2026-01-01', 40, 'panic', 0),
(22345678, 'Earthquake prediction tonight', 'Twitter', '2026-01-02', 20, 'panic', 0),
(32345678, 'Government tax increase rumor', 'Line Group', '2026-01-03', 70, 'normal', 1),
(42345678, 'Free electricity for one month', 'TikTok', '2026-01-04', 60, 'normal', 1),
(52345678, 'Fake bank SMS warning', 'SMS', '2026-01-05', 30, 'panic', 0),
(62345678, 'Fuel price hike tomorrow', 'Facebook', '2026-01-06', 55, 'normal', 0),
(72345678, 'School closure nationwide', 'Twitter', '2026-01-07', 25, 'panic', 0),
(82345678, 'New public holiday announced', 'News Website', '2026-01-08', 85, 'normal', 1);


INSERT INTO reports (user_id, rumour_id, report_type, reported_at) VALUES
-- Rumour 12345678 (3 reports → panic)
(1, 12345678, 'fake', '2026-01-02'),
(2, 12345678, 'incitement', '2026-01-02'),
(3, 12345678, 'distortion', '2026-01-03'),

-- Rumour 22345678 (4 reports → panic)
(1, 22345678, 'fake', '2026-01-03'),
(4, 22345678, 'fake', '2026-01-03'),
(5, 22345678, 'incitement', '2026-01-04'),
(6, 22345678, 'distortion', '2026-01-04'),

-- Rumour 52345678 (3 reports → panic)
(2, 52345678, 'fake', '2026-01-05'),
(3, 52345678, 'fake', '2026-01-05'),
(4, 52345678, 'incitement', '2026-01-06'),

-- Rumour 62345678 (1 report → normal)
(7, 62345678, 'distortion', '2026-01-07');


INSERT INTO verifications (rumour_id, verifier_id, result, verified_at) VALUES
(32345678, 9, 'false', '2026-01-06'),
(42345678, 9, 'false', '2026-01-06'),
(82345678, 10, 'true', '2026-01-07');
