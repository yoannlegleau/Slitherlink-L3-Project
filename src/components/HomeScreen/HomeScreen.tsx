import { memo } from 'react';
import type { FC } from 'react';

import resets from '../_resets.module.css';
import { Ellipse2Icon } from './Ellipse2Icon.js';
import { ExitToAppIcon } from './ExitToAppIcon.js';
import classes from './HomeScreen.module.css';
import { SettingsIcon } from './SettingsIcon.js';
import { VectorIcon } from './VectorIcon.js';

interface Props {
  className?: string;
}
/* @figmaId 10:177 */
export const HomeScreen: FC<Props> = memo(function HomeScreen(props = {}) {
  return (
    <div className={`${resets.clapyResets} ${classes.root}`}>
      <div className={classes.footer}></div>
      <div className={classes.slitherLink}>SlitherLink</div>
      <div className={classes.rectangle1}></div>
      <div className={classes.jouer}>Jouer</div>
      <div className={classes.vector}>
        <VectorIcon className={classes.icon} />
      </div>
      <div className={classes.rectangle12}></div>
      <div className={classes.tutoriel}>Tutoriel</div>
      <div className={classes.rectangle13}></div>
      <div className={classes.regles}>Règles</div>
      <div className={classes.askQuestion}></div>
      <div className={classes.rules}></div>
      <div className={classes.rectangle14}></div>
      <div className={classes.rectangle2}></div>
      <div className={classes.parametres}>Paramètres</div>
      <div className={classes.settings}>
        <SettingsIcon className={classes.icon2} />
      </div>
      <div className={classes.ellipse2}>
        <Ellipse2Icon className={classes.icon3} />
      </div>
      <div className={classes.image2}></div>
      <div className={classes.rectangle15}></div>
      <div className={classes.rectangle22}></div>
      <div className={classes.statistiques}>Statistiques</div>
      <div className={classes.rectangle16}></div>
      <div className={classes.rectangle23}></div>
      <div className={classes.statistiques2}>
        <div className={classes.textBlock}>Profil</div>
        <div className={classes.textBlock2}>
          <p></p>
        </div>
      </div>
      <div className={classes.rectangle17}></div>
      <div className={classes.rectangle24}></div>
      <div className={classes.statistiques3}>Quitter</div>
      <div className={classes.user}></div>
      <div className={classes.exitToApp}>
        <ExitToAppIcon className={classes.icon4} />
      </div>
    </div>
  );
});
