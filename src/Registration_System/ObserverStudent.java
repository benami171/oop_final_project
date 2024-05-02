package Registration_System;

/** every student implements this interface to be able to get updated on the course status
 */

public interface ObserverStudent {
    void update(Course course);
}
