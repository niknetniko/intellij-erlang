/*
 * Copyright 2012-2015 Sergey Ignatov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intellij.erlang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.usageView.UsageViewUtil;
import org.intellij.erlang.psi.ErlangCompositeElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ErlangCompositeElementImpl extends ASTWrapperPsiElement implements ErlangCompositeElement {
  public ErlangCompositeElementImpl(ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return getNode().getElementType().toString();
  }

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
    return processDeclarations(this, processor, state, lastParent, place);
  }

  static boolean processDeclarations(@NotNull PsiElement element, @NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
    return processor.execute(element, state) && ResolveUtil.processChildren(element, processor, state, lastParent, place);
  }

  @Override
  public ItemPresentation getPresentation() {
    final String text = UsageViewUtil.createNodeText(this);
    return new ItemPresentation() {
      @NotNull
      @Override
      public String getPresentableText() {
        return text;
      }

      @NotNull
      @Override
      public String getLocationString() {
        return getContainingFile().getName();
      }

      @Nullable
      @Override
      public Icon getIcon(boolean b) {
        return ErlangCompositeElementImpl.this.getIcon(0);
      }
    };
  }
}
