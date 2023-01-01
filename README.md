====# VideoPlayer

====https://youtu.be/fqDH1ImkC58

====https://medium.com/@prepnew/showing-subtitles-lyrics-on-android-exoplayer-98a88a7b53b2

====https://exoplayer.dev/playlists.html

=====https://youtu.be/VP0Gp3FfhQQ



https://gist.github.com/WrathChaos/d938328b6fa296f2b9590c2092733eae


https://medium.com/@prepnew/showing-subtitles-lyrics-on-android-exoplayer-98a88a7b53b2


https://stackoverflow.com/questions/70170842/exoplayer-enable-subtitle-on-video


https://exoplayer.dev/playlists.html


    Bitmap drawable_from_url(String url) throws java.net.MalformedURLException, java.io.IOException {

        HttpURLConnection connection = (HttpURLConnection)new URL(url) .openConnection();
        connection.setRequestProperty("User-agent","Mozilla/4.0");

        connection.connect();
        InputStream input = connection.getInputStream();

        return BitmapFactory.decodeStream(input);
    }



https://github.com/moneytoo/Player/blob/master/app/src/main/res/layout/exo_player_view.xml


https://github.com/moneytoo/Player/blob/master/app/src/main/java/com/brouken/player/BrightnessControl.java


https://github.com/moneytoo/Player/blob/58e63e275d2459a3ac06c742361468cbe4618237/app/src/main/java/com/brouken/player/PlayerActivity.java#L567




