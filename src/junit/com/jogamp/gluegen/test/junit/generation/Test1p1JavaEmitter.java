/**
 * Copyright 2010 JogAmp Community. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY JogAmp Community ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JogAmp Community OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of JogAmp Community.
 */

package com.jogamp.gluegen.test.junit.generation;

import java.io.IOException;

import com.jogamp.gluegen.test.junit.generation.impl.Bindingtest1p1Impl;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Michael Bien
 * @author Sven Gothel
 */
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test1p1JavaEmitter extends BaseClass {

    /**
     * Verifies loading of the new library.
     */
    @BeforeClass
    public static void chapter__TestLoadLibrary() throws Exception {
        BindingJNILibLoader.loadBindingtest1p1();
    }

    /**
     * Verifies the existence and creation of the generated class.
     */
    @Test
    public void chapter00TestClassExist() throws Exception {
        testClassExist("test1p1");
    }

    /**
     * Verifies if all generated method signatures are completed,
     * ie a compilation only coverage test without functional tests.
     */
    public void chapter__TestCoverageSignature() throws Exception {
        chapter__TestCoverageSignature(new Bindingtest1p1Impl());
    }

    /**
     * Verifies if all generated static constant values are completed,
     * and whether their value is as expected!
     * <p>
     * Covers all enumerates and defines.
     * </p>
     */
    @Test
    public void chapter01TestStaticConstants() throws Exception {
        chapter01TestStaticConstants(new Bindingtest1p1Impl());
    }

    /**
     * Verifies if all methods / signatures are properly generated,
     * can be invoked and functions.
     * This is a compilation (coverage) and runtime time (semantic) test.
     * This covers indirect primitive arrays and direct NIO buffers.
     */
    @Test
    public void chapter03aTestCoverageFunctionalityDirectNIOAndPrimitiveArray() throws Exception {
        chapter03TestCoverageFunctionalityNIOAndPrimitiveArray(new Bindingtest1p1Impl(), true);
    }

    /**
     * Verifies if all methods / signatures are properly generated,
     * can be invoked and functions.
     * This is a compilation (coverage) and runtime time (semantic) test.
     * This covers indirect primitive arrays and indirect NIO buffers (nio using arrays).
     */
    @Test
    public void chapter03bTestCoverageFunctionalityIndirectNIOAndPrimitiveArray() throws Exception {
        chapter03TestCoverageFunctionalityNIOAndPrimitiveArray(new Bindingtest1p1Impl(), false);
    }

    /**
     * This covers direct / indirect pointer buffers
     */
    @Test
    public void chapter04TestPointerBuffer() throws Exception {
        chapter04TestPointerBuffer(new Bindingtest1p1Impl());
    }

    /**
     * This covers indirect primitive arrays and indirect NIO buffers.
     */
    @Test
    public void chapter05TestSomeFunctionsAllIndirect() throws Exception {
        chapter05TestSomeFunctionsAllIndirect(new Bindingtest1p1Impl());
    }

    /**
     * This covers compounds (structs) data alignment
     */
    @Test
    public void chapter09TestCompoundAlignment() throws Exception {
        chapter09TestCompoundAlignment(new Bindingtest1p1Impl());
    }

    /**
     * This covers compounds (structs) call-by-reference
     */
    @Test
    public void chapter10TestCompoundCallByReference() throws Exception {
        chapter10TestCompoundCallByReference(new Bindingtest1p1Impl());
    }

    /**
     * This covers compounds (structs) call-by-value
     */
    @Test
    public void chapter11TestCompoundCallByValue() throws Exception {
        chapter11TestCompoundCallByValue(new Bindingtest1p1Impl());
    }

    /**
     * Test compound access read-write
     */
    @Test
    public void chapter12TestTKField() throws Exception {
        chapter12TestTKField(new Bindingtest1p1Impl());
    }

    /**
     * Test compound access read-only
     */
    @Test
    public void chapter13TestTKFieldImmutable() throws Exception {
        chapter13TestTKFieldImmutable(new Bindingtest1p1Impl());
    }

    @Test
    public void chapter15TestTKMixed() throws Exception {
        chapter15TestTKMixed(new Bindingtest1p1Impl());
    }

    /**
     * Test Custom JNI Code invocation
     */
    @Test
    public void chapter14TestCustomJNICode() throws Exception {
        chapter14TestCustomJNICode(new Bindingtest1p1Impl());
    }

    public static void main(final String args[]) throws IOException {
        final String tstname = Test1p1JavaEmitter.class.getName();
        org.junit.runner.JUnitCore.main(tstname);
    }

}
