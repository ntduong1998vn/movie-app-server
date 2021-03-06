/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.constant;

public final class StaticValue {
    public static final String AVATAR = "avatar/";
    public static final String GALLERY = "gallery/";
    public static final String POSTER = "poster/";
    public static final String JPEG = "image/jpeg";
    public static final String PNG = "image/png";
    public static final String EMPTY_STRING = "";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";
    public enum SourceType {
        YOUTUBE,
        JWPLAYER,
        GGDRIVE,
        LOTUS
    }

    public enum AuthProvider {
        LOCAL,
        FACEBOOK,
        GOOGLE,
        GITHUB


    }

    public enum Resolution {

    }
}
