package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

/*
 * Author : Sabelo Kama (219149364)
 * Date : 22 April 2024
 * */

@MappedSuperclass
public abstract class Application {

    public enum Status{
        Pending, Accepted, Rejected;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long appNo;
    protected LocalDate date;
    protected String status;

    public Application(){}


    public long getAppNo() {
        return appNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Application{" +
                "appNo='" + appNo + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return appNo == that.appNo && Objects.equals(date, that.date) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appNo, date, status);
    }
}
