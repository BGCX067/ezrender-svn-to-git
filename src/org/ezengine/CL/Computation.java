package org.ezengine.CL;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.opencl.*;
import static org.lwjgl.opencl.CL10.*;

public class Computation {

	private static CLContext context;
	private static CLCommandQueue queue;
	private static List<CLDevice> devices;

	public static final int DEVICE_TYPE_ALL = CL_DEVICE_TYPE_ALL, DEVICE_TYPE_CPU = CL_DEVICE_TYPE_CPU, DEVICE_TYPE_GPU = CL_DEVICE_TYPE_GPU, DEVICE_TYPE_ACCELERATOR = CL_DEVICE_TYPE_ACCELERATOR;

	public static void initialize(int DEVICE_TYPE) {
		try {
			CL.create();
			CLPlatform platform = CLPlatform.getPlatforms().get(0);
			devices = platform.getDevices(DEVICE_TYPE);
			context = CLContext.create(platform, devices, null, null, null);
			queue = clCreateCommandQueue(context, devices.get(0), CL_QUEUE_PROFILING_ENABLE, null);
		} catch (Exception e) {}
	}

	public static CLProgram buildProgram(String source) {
		CLProgram program = clCreateProgramWithSource(context, source, null);
		Util.checkCLError(clBuildProgram(program, devices.get(0), "", null));
		return program;
	}

	public static CLKernel buildKernel(CLProgram program, String kernel_name) {
		return clCreateKernel(program, kernel_name, null);
	}

	public static Buffer compute(CLKernel kernel, CLMem in, Buffer output) throws Exception {
		PointerBuffer pointer = BufferUtils.createPointerBuffer(1);
		CLMem out = allocateWriteMemory(output);
		if (output instanceof FloatBuffer) {
			pointer.put(0, ((FloatBuffer) output).capacity());
		} else if (output instanceof DoubleBuffer) {
			pointer.put(0, ((DoubleBuffer) output).capacity());
		} else if (output instanceof IntBuffer) {
			pointer.put(0, ((IntBuffer) output).capacity());
		} else if (output instanceof ShortBuffer) {
			pointer.put(0, ((ShortBuffer) output).capacity());
		} else if (output instanceof ByteBuffer) {
			pointer.put(0, ((ByteBuffer) output).capacity());
		} else if (output instanceof LongBuffer) {
			pointer.put(0, ((LongBuffer) output).capacity());
		}
		kernel.setArg(0, in);
		kernel.setArg(1, out);
		clEnqueueNDRangeKernel(queue, kernel, 1, null, pointer, null, null, null);
		if (output instanceof FloatBuffer) {
			clEnqueueReadBuffer(queue, out, 1, 0, (FloatBuffer) output, null, null);
		} else if (output instanceof DoubleBuffer) {
			clEnqueueReadBuffer(queue, out, 1, 0, (DoubleBuffer) output, null, null);
		} else if (output instanceof IntBuffer) {
			clEnqueueReadBuffer(queue, out, 1, 0, (IntBuffer) output, null, null);
		} else if (output instanceof ShortBuffer) {
			clEnqueueReadBuffer(queue, out, 1, 0, (ShortBuffer) output, null, null);
		} else if (output instanceof ByteBuffer) {
			clEnqueueReadBuffer(queue, out, 1, 0, (ByteBuffer) output, null, null);
		} else if (output instanceof LongBuffer) {
			clEnqueueReadBuffer(queue, out, 1, 0, (LongBuffer) output, null, null);
		}

		clFinish(queue);
		CLProgram program = kernel.getParent();
		clReleaseKernel(kernel);
		clReleaseProgram(program);
		clReleaseMemObject(in);
		clReleaseMemObject(out);
		return output;
	}

	public static CLMem allocateReadMemory(Buffer in) {
		CLMem mem = null;
		if (in instanceof FloatBuffer) {
			mem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, (FloatBuffer) in, null);
			clEnqueueWriteBuffer(queue, mem, 1, 0, (FloatBuffer) in, null, null);
		} else if (in instanceof DoubleBuffer) {
			mem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, (DoubleBuffer) in, null);
			clEnqueueWriteBuffer(queue, mem, 1, 0, (DoubleBuffer) in, null, null);
		} else if (in instanceof IntBuffer) {
			mem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, (IntBuffer) in, null);
			clEnqueueWriteBuffer(queue, mem, 1, 0, (IntBuffer) in, null, null);
		} else if (in instanceof ShortBuffer) {
			mem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, (ShortBuffer) in, null);
			clEnqueueWriteBuffer(queue, mem, 1, 0, (ShortBuffer) in, null, null);
		} else if (in instanceof ByteBuffer) {
			mem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, (ByteBuffer) in, null);
			clEnqueueWriteBuffer(queue, mem, 1, 0, (ByteBuffer) in, null, null);
		} else if (in instanceof LongBuffer) {
			mem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, (LongBuffer) in, null);
			clEnqueueWriteBuffer(queue, mem, 1, 0, (LongBuffer) in, null, null);
		}
		clFinish(queue);
		return mem;
	}

	public static CLMem allocateWriteMemory(Buffer out) {
		CLMem mem = null;
		if (out instanceof FloatBuffer) {
			mem = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, (FloatBuffer) out, null);
		} else if (out instanceof DoubleBuffer) {
			mem = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, (DoubleBuffer) out, null);
		} else if (out instanceof IntBuffer) {
			mem = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, (IntBuffer) out, null);
		} else if (out instanceof ShortBuffer) {
			mem = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, (ShortBuffer) out, null);
		} else if (out instanceof ByteBuffer) {
			mem = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, (ByteBuffer) out, null);
		} else if (out instanceof LongBuffer) {
			mem = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, (LongBuffer) out, null);
		}
		clFinish(queue);
		return mem;
	}

}
