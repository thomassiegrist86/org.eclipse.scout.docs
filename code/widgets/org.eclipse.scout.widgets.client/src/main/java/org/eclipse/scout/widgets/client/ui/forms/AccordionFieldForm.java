package org.eclipse.scout.widgets.client.ui.forms;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.rt.client.ui.accordion.AbstractAccordion;
import org.eclipse.scout.rt.client.ui.accordion.IAccordion;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.fields.GridData;
import org.eclipse.scout.rt.client.ui.form.fields.accordionfield.AbstractAccordionField;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.group.AbstractGroup;
import org.eclipse.scout.rt.client.ui.group.IGroup;
import org.eclipse.scout.rt.client.ui.tile.AbstractTiles;
import org.eclipse.scout.rt.client.ui.tile.ITile;
import org.eclipse.scout.rt.client.ui.tile.ITiles;
import org.eclipse.scout.rt.client.ui.tile.TilesLayoutConfig;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.tile.TileColorScheme;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.IAdvancedExampleForm;
import org.eclipse.scout.widgets.client.ui.forms.AccordionFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widgets.client.ui.forms.AccordionFieldForm.MainBox.DetailBox;
import org.eclipse.scout.widgets.client.ui.forms.AccordionFieldForm.MainBox.DetailBox.AccordionField;
import org.eclipse.scout.widgets.client.ui.forms.AccordionFieldForm.MainBox.DetailBox.AccordionField.Accordion;
import org.eclipse.scout.widgets.client.ui.forms.AccordionFieldForm.MainBox.PropertiesBox;
import org.eclipse.scout.widgets.client.ui.forms.AccordionFieldForm.MainBox.PropertiesBox.ExclusiveExpandField;
import org.eclipse.scout.widgets.client.ui.forms.AccordionFieldForm.MainBox.PropertiesBox.ScrollableField;
import org.eclipse.scout.widgets.client.ui.forms.TilesFieldForm.SimpleTile;

@ClassId("59689d49-e6a5-4641-9d1a-a6a6ce98d9bf")
public class AccordionFieldForm extends AbstractForm implements IAdvancedExampleForm {

  private int m_groupsAddedCount = 0;

  @Override
  public void startPageForm() {
    start();
  }

  public ScrollableField getScrollableField() {
    return getFieldByClass(ScrollableField.class);
  }

  public AccordionField getAccordionField() {
    return getFieldByClass(AccordionField.class);
  }

  public DetailBox getDetailBox() {
    return getFieldByClass(DetailBox.class);
  }

  public ExclusiveExpandField getExclusiveExpandField() {
    return getFieldByClass(ExclusiveExpandField.class);
  }

  public PropertiesBox getPropertiesBox() {
    return getFieldByClass(PropertiesBox.class);
  }

  @Override
  public AbstractCloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  @Override
  protected String getConfiguredTitle() {
    return "AccordionField";
  }

  @ClassId("4d6137e0-7d67-491b-969f-c02c3161581b")
  public class MainBox extends AbstractGroupBox {

    @Order(10)
    @ClassId("41a25783-a1cb-4895-a417-73eebfb1b804")
    public class DetailBox extends AbstractGroupBox {

      @Order(1000)
      @ClassId("e4efa1c6-b6b0-4159-bf68-5fcb852bbaa5")
      public class AddMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return "Add group";
        }

        @Override
        protected void execAction() {
          addGroupWithTiles();
        }
      }

      @Order(1100)
      @ClassId("64f7eb9c-bedd-4680-8b94-555c9c64fd97")
      public class DeleteFirstMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return "Delete first group";
        }

        @Override
        protected void execAction() {
          Accordion accordion = getAccordionField().getAccordion();
          if (accordion.getGroupCount() == 0) {
            return;
          }
          accordion.deleteGroup(accordion.getGroups().get(0));
          if (accordion.getGroupCount() == 0) {
            m_groupsAddedCount = 0;
          }
        }

      }

      @Order(1200)
      @ClassId("b0bea1fd-0de8-4695-a026-d9c7d290d997")
      public class ExpandCollapseFirstMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return "Expand/Collapse first group";
        }

        @Override
        protected void execAction() {
          Accordion accordion = getAccordionField().getAccordion();
          if (accordion.getGroupCount() == 0) {
            return;
          }
          accordion.getGroups().get(0).toggleCollapse();
        }
      }

      @Order(1300)
      @ClassId("09d44b29-d1b9-427b-af2a-89825f3f31f3")
      public class CollapseAllMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return "Collapse all groups";
        }

        @Override
        protected void execAction() {
          for (IGroup group : getAccordionField().getAccordion().getGroups()) {
            group.setCollapsed(true);
          }
        }
      }

      @Order(1000)
      @ClassId("0b9d8c2e-b58b-4d3b-8f37-bb3f4ac075fb")
      public class AccordionField extends AbstractAccordionField<AccordionField.Accordion> {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return FULL_WIDTH;
        }

        @ClassId("269a1b03-5af1-4cf8-a063-20a9bbe85ae8")
        public class Accordion extends AbstractAccordion {

        }
      }

    }

    @Order(2000)
    @ClassId("d556cf3b-699f-4c9f-a6a0-7eac28398cd1")
    public class PropertiesBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return "Properties";
      }

      @Order(20)
      @ClassId("f82d22e3-944e-4f25-afdc-bb1736d530c8")
      public class ExclusiveExpandField extends AbstractBooleanField {
        @Override
        protected String getConfiguredLabel() {
          return "Exclusive Expand";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() {
          getAccordionField().getAccordion().setExclusiveExpand(getValue());
        }

        @Override
        protected void execInitField() {
          setValue(getAccordionField().getAccordion().isExclusiveExpand());
        }
      }

      @Order(38)
      @ClassId("e3725a0d-f6ea-4115-b892-703890962114")
      public class ScrollableField extends AbstractBooleanField {
        @Override
        protected String getConfiguredLabel() {
          return "Scrollable";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() {
          getAccordionField().getAccordion().setScrollable(getValue());
        }

        @Override
        protected void execInitField() {
          setValue(getAccordionField().getAccordion().isScrollable());
        }
      }

    }

    @Order(50)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  protected void addGroupWithTiles() {
    IAccordion accordion = getAccordionField().getAccordion();
    List<ITile> tiles = new ArrayList<>();
    int maxTiles = new SecureRandom().nextInt(30);
    for (int i = 0; i < maxTiles; i++) {
      SimpleTile tile = new SimpleTile();
      tile.setLabel("Tile " + i);
      GridData gridDataHints = tile.getGridDataHints();
      gridDataHints.weightX = 0;
      tile.setGridDataHints(gridDataHints);
      tile.setColorScheme(accordion.getGroupCount() * 2 == 0 ? TileColorScheme.DEFAULT : TileColorScheme.ALTERNATIVE);
      tiles.add(tile);
    }
    TileGroup group = new TileGroup();
    group.setTitle("Group " + m_groupsAddedCount++);
    group.getBody().setTiles(tiles);
    accordion.addGroup(group);
  }

  @Override
  protected void execInitForm() {
    super.execInitForm();
    addGroupWithTiles();
    addGroupWithTiles();
    addGroupWithTiles();
  }

  public static class TileGroup extends AbstractGroup {

    @Override
    public ITiles getBody() {
      return (ITiles) super.getBody();
    }

    @ClassId("1af3bcc9-5cb0-486a-bb5a-6ef5dfc63230")
    public class Tiles extends AbstractTiles {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 6;
      }

      @Override
      protected TilesLayoutConfig getConfiguredLayoutConfig() {
        return super.getConfiguredLayoutConfig()
            .withColumnWidth(100)
            .withRowHeight(100);
      }

      @Override
      protected boolean getConfiguredScrollable() {
        return false;
      }
    }

  }

}
