/**
 * Copyright 2019 JogAmp Community. All rights reserved.
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

import com.jogamp.gluegen.test.junit.generation.impl.Bindingtest1p1Impl;
import com.jogamp.gluegen.test.junit.generation.impl.Bindingtest1p2Impl;
import com.jogamp.common.os.DynamicLibraryBundle;
import com.jogamp.common.os.DynamicLibraryBundleInfo;
import com.jogamp.common.os.NativeLibrary;
import com.jogamp.common.util.RunnableExecutor;
import com.jogamp.common.util.TestIOUtil01;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test1p2DynamicLibraryBundle extends BaseClass {

    static DynamicLibraryBundle dlb;

    /**
     * Verifies loading of the new library.
     */
    @BeforeClass
    public static void chapter__TestLoadLibrary() throws Exception {
        dlb = new DynamicLibraryBundle(new Test1DynLibBundleInfo());
        Assert.assertTrue("DynamicLibraryBundle failed", dlb.isLibComplete());

        Bindingtest1p2Impl.resetProcAddressTable(dlb);
    }

    /**
     * Verifies the existence and creation of the generated class.
     */
    @Test
    public void chapter00TestClassExist() throws Exception {
        testClassExist("test1p2");
    }

    /**
     * Verifies if all generated method signatures are completed,
     * ie a compilation only coverage test without functional tests.
     */
    public void chapter__TestCoverageSignature() throws Exception {
        chapter__TestCoverageSignature(new Bindingtest1p2Impl());
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
        chapter01TestStaticConstants(new Bindingtest1p2Impl());
    }

    /**
     * Verifies if all methods / signatures are properly generated,
     * can be invoked and functions.
     * This is a compilation (coverage) and runtime time (semantic) test.
     * This covers indirect primitive arrays and direct NIO buffers.
     */
    @Test
    public void chapter03aTestCoverageFunctionalityDirectNIOAndPrimitiveArray() throws Exception {
        chapter03TestCoverageFunctionalityNIOAndPrimitiveArray(new Bindingtest1p2Impl(), true);
    }

    /**
     * Verifies if all methods / signatures are properly generated,
     * can be invoked and functions.
     * This is a compilation (coverage) and runtime time (semantic) test.
     * This covers indirect primitive arrays and indirect NIO buffers (nio using arrays).
     */
    @Test
    public void chapter03bTestCoverageFunctionalityIndirectNIOAndPrimitiveArray() throws Exception {
        chapter03TestCoverageFunctionalityNIOAndPrimitiveArray(new Bindingtest1p2Impl(), false);
    }

    /**
     * This covers direct / indirect pointer buffers
     */
    @Test
    public void chapter04TestPointerBuffer() throws Exception {
        this.chapter04TestPointerBuffer(new Bindingtest1p2Impl());
    }

    /**
     * This covers indirect primitive arrays and indirect NIO buffers.
     */
    @Test
    public void chapter05TestSomeFunctionsAllIndirect() throws Exception {
        chapter05TestSomeFunctionsAllIndirect(new Bindingtest1p2Impl());
    }

    /**
     * This covers compounds (structs) data alignment
     */
    @Test
    public void chapter09TestCompoundAlignment() throws Exception {
        chapter09TestCompoundAlignment(new Bindingtest1p2Impl());
    }

    /**
     * This covers compounds (structs) call-by-reference
     */
    @Test
    public void chapter10TestCompoundCallByReference() throws Exception {
        chapter10TestCompoundCallByReference(new Bindingtest1p2Impl());
    }

    /**
     * This covers compounds (structs) call-by-value
     */
    @Test
    public void chapter11TestCompoundCallByValue() throws Exception {
        chapter11TestCompoundCallByValue(new Bindingtest1p2Impl());
    }

    /**
     * Test compound access read-write
     */
    @Test
    public void chapter12TestTKField() throws Exception {
        chapter12TestTKField(new Bindingtest1p2Impl());
    }

    /**
     * Test compound access read-only
     */
    @Test
    public void chapter13TestTKFieldImmutable() throws Exception {
        chapter13TestTKFieldImmutable(new Bindingtest1p2Impl());
    }

    @Test
    public void chapter15TestTKMixed() throws Exception {
        chapter15TestTKMixed(new Bindingtest1p2Impl());
    }

    /**
     * Test Custom JNI Code invocation
     */
    @Test
    public void chapter14TestCustomJNICode() throws Exception {
        chapter14TestCustomJNICode(new Bindingtest1p2Impl());
    }

    /**
     * Verifies unloading of the new library.
     */
    @AfterClass
    public static void chapter0XTestUnloadLibrary() throws Exception {
        Assert.assertNotNull(dlb);
        dlb.destroy();
        dlb = null;
    }

    @SuppressWarnings("unused")
    public static void main(final String args[]) throws Exception {
        if( false ) {
            chapter__TestLoadLibrary();
            final Test1p2DynamicLibraryBundle tst = new Test1p2DynamicLibraryBundle();
            tst.chapter00TestClassExist();
        } else {
            final String tstname = Test1p2DynamicLibraryBundle.class.getName();
            org.junit.runner.JUnitCore.main(tstname);
        }
    }

    public static class Test1DynLibBundleInfo implements DynamicLibraryBundleInfo  {
        private static final List<String> glueLibNames;
        static {
            glueLibNames = new ArrayList<String>();
            glueLibNames.add("Bindingtest1p2");
        }

        protected Test1DynLibBundleInfo() {
        }

        /**
         * <p>
         * Returns <code>true</code>,
         * since we might load the library and allow symbol access to subsequent libs.
         * </p>
         */
        @Override
        public final boolean shallLinkGlobal() { return true; }

        /**
         * {@inheritDoc}
         * <p>
         * Returns <code>false</code>.
         * </p>
         */
        @Override
        public final boolean shallLookupGlobal() { return false; }

        @Override
        public final List<String> getGlueLibNames() {
            return glueLibNames;
        }

        @Override
        public final boolean searchToolLibInSystemPath() {
            return true;
        }

        @Override
        public final boolean searchToolLibSystemPathFirst() {
            return true;
        }

        @Override
        public final List<List<String>> getToolLibNames() {
            final List<List<String>> libNamesList = new ArrayList<List<String>>();

            final List<String> libNames = new ArrayList<String>();
            {
                // this is the default AL lib name, according to the spec
                // libNames.add("libtest1.so"); // unix
                libNames.add("test1"); // windows, OSX
            }
            libNamesList.add(libNames);

            return libNamesList;
        }

        @Override
        public final List<String> getToolGetProcAddressFuncNameList() {
            return null;
        }

        @Override
        public final long toolGetProcAddress(final long toolGetProcAddressHandle, final String funcName) {
            return 0;
        }

        @Override
        public final boolean useToolGetProcAdressFirst(final String funcName) {
            return false;
        }

        @Override
        public final RunnableExecutor getLibLoaderExecutor() {
            return DynamicLibraryBundle.getDefaultRunnableExecutor();
        }
    }

}
