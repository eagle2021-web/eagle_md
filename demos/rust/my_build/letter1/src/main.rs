use lettre::smtp::authentication::Credentials;
use lettre::{SmtpClient, Transport};
use lettre_email::{EmailBuilder, Mailbox};

fn main() {
    let email = EmailBuilder::new()
        .from(Mailbox::new("chenwangying16@163.com".to_string()))
        //.from(Mailbox::new("xiaoming@163.com".to_string())) //发送者：xiaoming@163.com
        .to(Mailbox::new("1280702131@qq.com".to_string()))
        //.to(Mailbox::new("xiaohong@126.com".to_string())) //接收者：xiaohong@126.com
        .subject("Test") //邮件标题
        .body("This is a test email!") //邮件内容
        .build()
        .unwrap();

    //for example: xiaoming@163.com, password: 123456
    //let creds = Credentials::new("xiaoming".to_string(), "123456".to_string());
    let creds = Credentials::new("chenwangying16@163.com".to_string(), "a".to_string());

    //如163的邮箱就是smtp.163.com, 126的邮箱就是smtp.126.com
    let mut mailer = SmtpClient::new_simple("smtp.163.com")
        .unwrap()
        .credentials(creds)
        .transport();

    let result = mailer.send(email.into());

    if result.is_ok() {
        println!("Email sent");
    } else {
        println!("Could not send email: {:?}", result);
    }

    assert!(result.is_ok());
}
