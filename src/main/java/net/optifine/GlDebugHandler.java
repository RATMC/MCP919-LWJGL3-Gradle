package net.optifine;

import java.nio.IntBuffer;
import net.minecraft.src.Config;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.GLDebugMessageCallbackI;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.system.MemoryUtil;

public class GlDebugHandler
{
    public static void createDisplayDebug() throws LWJGLException
    {
        boolean flag = GLContext.getCapabilities().GL_ARB_debug_output;
        Display.create((new PixelFormat()).withDepthBits(24));
        
        if (flag)
        {
            GL11.glEnable(33346); // GL_DEBUG_OUTPUT
            KHRDebug.glDebugMessageCallback(new GLDebugMessageCallbackI() {
                @Override
                public void invoke(int source, int type, int id, int severity, int length, long message, long userParam) {
                    handleMessage(source, type, id, severity, length, message, userParam);
                }
            }, 0L);
        }
    }

    public static void handleMessage(int source, int type, int id, int severity, int length, long message, long userParam)
    {
        String msg = MemoryUtil.memUTF8(message, length);
        if (!msg.contains("glBindFramebuffer"))
        {
            if (!msg.contains("Wide lines"))
            {
                if (!msg.contains("shader recompiled"))
                {
                    Config.dbg("[LWJGL] source: " + getSource(source) + ", type: " + getType(type) + ", id: " + id + ", severity: " + getSeverity(severity) + ", message: " + msg);
                    (new Throwable("StackTrace")).printStackTrace();
                }
            }
        }
    }

    public static String getSource(int source)
    {
        switch (source)
        {
            case 33350:
                return "API";

            case 33351:
                return "WIN";

            case 33352:
                return "SHADER";

            case 33353:
                return "EXT";

            case 33354:
                return "APP";

            case 33355:
                return "OTHER";

            default:
                return getUnknown(source);
        }
    }

    public static String getType(int type)
    {
        switch (type)
        {
            case 33356:
                return "ERROR";

            case 33357:
                return "DEPRECATED";

            case 33358:
                return "UNDEFINED";

            case 33359:
                return "PORTABILITY";

            case 33360:
                return "PERFORMANCE";

            case 33361:
                return "OTHER";

            default:
                return getUnknown(type);
        }
    }

    public static String getSeverity(int severity)
    {
        switch (severity)
        {
            case 37190:
                return "HIGH";

            case 37191:
                return "MEDIUM";

            case 37192:
                return "LOW";

            default:
                return getUnknown(severity);
        }
    }

    private static String getUnknown(int token)
    {
        return "Unknown (0x" + Integer.toHexString(token).toUpperCase() + ")";
    }
}