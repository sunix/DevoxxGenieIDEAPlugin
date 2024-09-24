package com.devoxx.genie.service;

import com.devoxx.genie.model.request.ChatMessageContext;
import org.jetbrains.annotations.NotNull;


/**
 * The message creation service for user and system messages.
 * Here's where also the basic prompt "engineering" is happening, including calling the AST magic.
 */
public class CliMessageCreationService extends AbstractMessageCreationService {

    private static final CliMessageCreationService INSTANCE = new CliMessageCreationService();

    public static CliMessageCreationService getInstance() {
        return INSTANCE;
    }


  

    /**
     * Add AST prompt context (selected code snippet) to the chat message.
     * @param chatMessageContext the chat message context
     * @param sb                 the string builder
     */
    void addASTContext(@NotNull ChatMessageContext chatMessageContext,
                                      @NotNull StringBuilder sb) {
    //     sb.append("\n\nRelated classes:\n\n");
    //     List<VirtualFile> tempFiles = new ArrayList<>();

    //     chatMessageContext.getEditorInfo().getSelectedFiles().forEach(file ->
    //         PSIAnalyzerService.getInstance().analyze(chatMessageContext.getProject().getAdaptedInstance(Project.class), file.getAdaptedInstance(VirtualFile.class))
    //             .ifPresent(psiClasses ->
    //                 psiClasses.forEach(psiClass -> {
    //                     tempFiles.add(psiClass.getContainingFile().getVirtualFile());
    //                     sb.append(psiClass.getText()).append("\n");
    //                 })));
    //    ;
    //     chatMessageContext.getEditorInfo().getSelectedFiles().addAll( tempFiles.stream().map((file) -> new IntellijVirtualFileHandler(file)).toList());
     }




    @Override
    protected void runReadAction(Runnable runnable) {
        runnable.run();
    }

}
