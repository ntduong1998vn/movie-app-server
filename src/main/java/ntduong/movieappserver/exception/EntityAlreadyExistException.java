/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.exception;

import lombok.Getter;

@Getter
public class EntityAlreadyExistException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public EntityAlreadyExistException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s has already existed with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
