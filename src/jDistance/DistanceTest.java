package jDistance;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({jDistance.ai.test.AiCompareTest.class, jDistance.ai.test.AiControllerTest.class, jDistance.ai.test.AiMoveTest.class, jDistance.ai.test.BasicAiControllerTest.class, jDistance.ai.test.DevelopAiControllerTest.class,
				jDistance.gui.GUITest.class, jDistance.gui.GuiTokenTest.class, jDistance.gui.ImageHandlerTest.class,
				jDistance.gui3d.test.Distance3DTest.class,
				jDistance.model.test.DistanceTest.class, jDistance.model.test.FieldTest.class, jDistance.model.test.KingTest.class, jDistance.model.test.PlayingGroundTest.class, jDistance.model.test.SlaveTest.class, jDistance.model.test.TokenMoveTest.class, jDistance.model.test.TokenTest.class, 
				jDistance.settings.test.SettingsTest.class, 
				jDistance.util.test.CloseEventTest.class, jDistance.util.test.ExpectTokenMoveEventTest.class, jDistance.util.test.GameStartedEventTest.class, jDistance.util.test.MessageHandlerTest.class, jDistance.util.test.MessageQueueTest.class, jDistance.util.test.MessageSenderTest.class, jDistance.util.test.MoveTokenEventTest.class, jDistance.util.test.StartGameEventTest.class, jDistance.util.test.TokenLockedEventTest.class, jDistance.util.test.TokenMovedEventTest.class, jDistance.util.test.TokenSwapedEventTest.class, jDistance.util.test.WinEventTest.class})
public class DistanceTest {

}