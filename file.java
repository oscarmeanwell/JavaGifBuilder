
//Project --> Properties --> Deployment Assembly --> Add -->> Java Build Path Entries --> Select Package --> Apply
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import net.jmge.gif.Gif89Encoder;
@WebServlet("/Temp")
public class temp1 extends HttpServlet {
 private static final long serialVersionUID = 1L;
 public temp1() {
  super();
 }
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  if(request.getParameter("inp1") == null)
  {
   buildform(request, response);
  }else
  {
   int starC = Math.abs(Integer.parseInt(request.getParameter("col")));
   int backG = Integer.parseInt(request.getParameter("bg"));
   int frmW = Integer.parseInt(request.getParameter("fw"));
   int frmL = Integer.parseInt(request.getParameter("fl"));
   int starR = Integer.parseInt(request.getParameter("rad"));
   int starN = Integer.parseInt(request.getParameter("num"));
   if (starC==0 || backG==0 || frmW==0 || frmL==0 || starR==0)
   {
    JOptionPane.showMessageDialog(null, "Cannot have value 0");
    buildform(request, response);
   }else
   {
    response.setContentType("image/gif");
    Gif89Encoder genc = new Gif89Encoder();
    for (int i=0;i<starN;i++)
    {
     BufferedImage image = new BufferedImage(frmL,frmW, BufferedImage.TYPE_INT_ARGB);
     Graphics2D g = image.createGraphics();
     g.setColor(new Color(Integer.parseInt(request.getParameter("bg"),16)));
     g.fillRect(0, 0, frmL, frmW);
     g.translate(frmL/2, frmW/2);
     Color color = new Color(Integer.parseInt(request.getParameter("starC"),16));
     drawSh(g,i,color,starR,frmL,frmW);
     genc.addFrame(image);
     g.dispose();
    }
    genc.setUniformDelay(5);
    genc.setLoopCount(0);
    genc.encode(response.getOutputStream());
   }
  }
 }
 private void buildform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  out.println("<body bgcolor='0099FF'");
  out.println("<head><h3>Spinner Generator</h3></head>");
  out.println("<table><form>");
  out.println("<tr><td>Star Color:</td> <td><input name='col' value = 'FF0000'/></td><td><pre>	Uses HTML color codes</pre><td></tr>");
  out.println("<tr><td>Background:</td><td> <input name='bg' value = 'FFFFFF'/></td><td><pre>	Uses HTML color codes</pre><td></tr>");
  out.println("<tr><td>Frame Lenght:</td><td> <input name='fl' value = '150'/></td><td><pre>	Use Integers</pre><td></tr>");
  out.println("<tr><td>Frame Width:</td><td> <input name='fw' value = '150'/></td><td><pre>	Use Integers</pre><td></tr>");
  out.println("<tr><td>Star Radius:</td><td> <input name='rad' value = '10'/></td><td><pre>	Use Integers</pre><td></tr>");
  out.println("<tr><td>Star Number:</td><td> <input name='rad' value = '10'/></td><td><pre>	Use Integers</pre><td></tr>");
  out.println("<tr><td><pre> </pre></td><td><pre>   <input type='submit'/></pre></td>");
  out.println("</form></table>");
  out.println("</body>");
 }
 private void drawSh(Graphics2D g, int i, Color color, int starR, int frmL, int frmW) {
  Random rand = new Random();
  g.rotate(i*6);
  g.setColor(color);
  Polygon star = new Polygon(
    new int[]{0,11,48,18,30,0,-30,-18,-48,-11},
    new int[]{-50,-16,-16,6,41,18,41,6,-16,-16},10);
  g.translate(rand.nextInt(frmL), rand.nextInt(frmW));
  g.fillPolygon(star);
 }
}




