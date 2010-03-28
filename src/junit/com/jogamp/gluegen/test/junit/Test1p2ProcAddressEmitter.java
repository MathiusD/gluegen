/*
 * Copyright (c) 2010 Sven Gothel. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * 
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * 
 * Neither the name Sven Gothel or the names of
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN
 * MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR
 * ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR
 * DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY,
 * ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF
 * SVEN GOTHEL HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 */

package com.jogamp.gluegen.test.junit;

import com.jogamp.gluegen.test.junit.impl.BindingTest1p2Impl;

import com.sun.gluegen.runtime.BufferFactory;
import com.sun.gluegen.runtime.PointerBuffer;
import com.sun.gluegen.runtime.NativeLibrary;
import com.sun.gluegen.runtime.DynamicLookupHelper;
import java.nio.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static java.lang.System.*;
import static com.jogamp.gluegen.test.junit.BuildEnvironment.*;

/**
 * @author Michael Bien
 * @author Sven Gothel
 */
public class Test1p2ProcAddressEmitter extends BaseTest1 {

    DynamicLookupHelper dynamicLookupHelper;

    /**
     * Verifies loading of the new library.
     */
    @Test
    public void chapter01TestLoadLibrary() throws Exception {
        System.loadLibrary("test1p2");
        dynamicLookupHelper = NativeLibrary.open("test1p2", getClass().getClassLoader(), true);
        Assert.assertNotNull("NativeLibrary.open(test1p2) failed", dynamicLookupHelper);

        BindingTest1p2Impl.resetProcAddressTable(dynamicLookupHelper);
    }

    /**
     * Verifies the existence and creation of the generated class.
     */
    @Test
    public void chapter02TestClassExist() throws Exception {
        testClassExist("Test1p2");
    }

    /**
     * Verifies if all generated method signatures are completed,
     * ie a compilation only coverage test without functional tests.
     */
    public void chapter__TestCoverageSignature() throws Exception {
        chapter__TestCoverageSignature(new BindingTest1p2Impl());
    }

    /**
     * Verifies if all methods / signatures are properly generated,
     * can be invoked and functions.
     * This is a compilation (coverage) and runtime time (semantic) test.
     * This covers indirect primitive arrays and direct NIO buffers.
     */
    @Test
    public void chapter03TestCoverageFunctionalityDirectNIOAndPrimitiveArray() throws Exception {
        chapter03TestCoverageFunctionalityDirectNIOAndPrimitiveArray(new BindingTest1p2Impl());
    }

    /**
     * This covers indirect primitive arrays and indirect NIO buffers.
     */
    @Test
    public void chapter04TestSomeFunctionsAllIndirect() throws Exception {
        chapter04TestSomeFunctionsAllIndirect(new BindingTest1p2Impl());
    }

    public static void main(String[] args) {
        Test1p2ProcAddressEmitter test = new Test1p2ProcAddressEmitter();
        try {
            test.chapter01TestLoadLibrary();
            test.chapter02TestClassExist();
            test.chapter03TestCoverageFunctionalityDirectNIOAndPrimitiveArray();
            test.chapter04TestSomeFunctionsAllIndirect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
