/*******************************************************************************
 * Copyright (c) 2010 - Christopher Saunders
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ******************************************************************************/
package ca.christophersaunders.tutorials.sqlite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import ca.christophersaunders.tutorials.sqlite.picasa.PicasaAlbum;
import ca.christophersaunders.tutorials.sqlite.picasa.PicasaHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SQLiteAndroidTutorialMainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try {
	        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
	        PicasaHandler picasaHandler = new PicasaHandler();
	        URL picasaFeed = new URL("http://picasaweb.google.com/data/feed/base/user/c.saunders322/albumid/5513471319225508497?alt=rss&kind=photo&hl=en_US");
	        parser.parse(picasaFeed.openStream(), picasaHandler);
	        PicasaAlbum album = picasaHandler.getParsedAlbum();
	        Log.d("Parser Results:", "There are " + album.getAlbumImages().size() + " images in the " + album.getTitle() +" album created by " + album.getAuthor());
        } catch (SAXException saxException) {
        	saxException.printStackTrace();
        } catch (MalformedURLException murlException) {
        	murlException.printStackTrace();
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        } catch (ParserConfigurationException pce) {
        	pce.printStackTrace();
        }
    }
}