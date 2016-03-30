/*    */ package autocode;
/*    */ 
/*    */ import java.awt.Window;
/*    */ import org.jdesktop.application.Application;
/*    */ import org.jdesktop.application.SingleFrameApplication;
/*    */ 
/*    */ public class AutoCodeApp extends SingleFrameApplication
/*    */ {
/*    */   protected void startup()
/*    */   {
/* 11 */     show(new AutoCodeView(this));
/*    */   }
/*    */ 
/*    */   protected void configureWindow(Window root)
/*    */   {
/*    */   }
/*    */ 
/*    */   public static AutoCodeApp getApplication()
/*    */   {
/* 20 */     return (AutoCodeApp)Application.getInstance(AutoCodeApp.class);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 25 */     launch(AutoCodeApp.class, args);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\AutoCode_V1.4\AutoCode_V1.4.jar
 * Qualified Name:     autocode.AutoCodeApp
 * JD-Core Version:    0.6.1
 */