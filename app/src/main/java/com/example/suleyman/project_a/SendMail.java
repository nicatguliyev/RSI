package com.example.suleyman.project_a;


import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail extends AsyncTask<Void, Void, Void> {


    private Context context;
    private Session session;

    private String email;
    private File file;
    private String type;

    private ProgressBar progressBar;
    private TextView sendingInformation;
    private ImageView doneImg, errorImage;
    private RelativeLayout okLyt;
    private RelativeLayout gonderLyt;

    String username = "rsiadyexpress";
    String password = "rsiady11";
    String error = "";


    public SendMail(Context context, String email, File file, ProgressBar progressBar, TextView sendingInformation, ImageView doneImg, ImageView errorImage, RelativeLayout okLyt,
                    RelativeLayout gonderLyt,
                     String type) {
        this.context = context;
        this.email = email;
        this.file = file;
        this.progressBar = progressBar;
        this.sendingInformation = sendingInformation;
        this.doneImg = doneImg;
        this.type = type;
        this.gonderLyt = gonderLyt;
        this.okLyt = okLyt;
        this.errorImage = errorImage;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

       gonderLyt.setVisibility(View.INVISIBLE);
       okLyt.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        progressBar.setVisibility(View.INVISIBLE);

      if(error.equals(""))
      {
        doneImg.setVisibility(View.VISIBLE);
        sendingInformation.setText("Göndərildi");
        sendingInformation.setTextColor(Color.GREEN);
        okLyt.setVisibility(View.VISIBLE);
        gonderLyt.setVisibility(View.INVISIBLE);
      }

       else
      {
        errorImage.setVisibility(View.VISIBLE);
        sendingInformation.setText("Göndərilmədi");
        sendingInformation.setTextColor(Color.RED);
        okLyt.setVisibility(View.INVISIBLE);
        gonderLyt.setVisibility(View.VISIBLE);
      }

    }

    @Override
    protected Void doInBackground(Void... voids) {


        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress("rsiadyexpress@gmail.com"));
            //Adding receiver
            mm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            if(type.equals("1")) {
              mm.setSubject("Günlük Hesabat");
            }
            if(type.equals("2"))
            {
              mm.setSubject("Ayliq Hesabat");
            }

            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText("");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String filename = file.getAbsolutePath();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
          //  messageBodyPart.setFileName(filename);
            messageBodyPart.setFileName(filename.substring(filename.lastIndexOf("/")+1));
            multipart.addBodyPart(messageBodyPart);

            mm.setContent(multipart);

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();

           error = "Xeta";

        }

        return null;
    }
}
