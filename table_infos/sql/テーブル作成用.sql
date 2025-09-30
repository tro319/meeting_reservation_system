CREATE TABLE reservations (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(300) NOT NULL,
    date DATETIME NOT NULL,
    time_id INT NOT NULL,
    user_id INT NOT NULL,
    interviewer_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (time_id) REFERENCES times(time_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (interviewer_id) REFERENCES interviewers(interviewer_id)
);
    


