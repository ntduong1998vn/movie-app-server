/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.service;

public interface IEmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
