import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import 'hammerjs';

import { FuseModule } from '@fuse/fuse.module';
import { FuseSharedModule } from '@fuse/shared.module';
import { FuseProgressBarModule, FuseSidebarModule, FuseThemeOptionsModule } from '@fuse/components';

import { fuseConfig } from 'app/fuse-config';

import { AppComponent } from 'app/app.component';
import { LayoutModule } from 'app/layout/layout.module';
import { routing } from './app.routing';
import { AppInterceptor } from './utils/app-interceptor';
import { MatPaginatorIntl } from '@angular/material';
import { getSpanishPaginatorIntl } from './utils/spanish-paginator-intl';
import { AuthGuard } from './utils/guards/auth.guard';
import { TranslateModule } from '@ngx-translate/core';

import { CURRENCY_MASK_CONFIG } from 'ng2-currency-mask/src/currency-mask.config';
import { CustomCurrencyMaskConfig } from './utils/customCurrencyMaskConfig';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        routing,

        TranslateModule.forRoot(),

        // Material moment date module
        MatMomentDateModule,

        // Material
        MatButtonModule,
        MatIconModule,

        // Fuse modules
        FuseModule.forRoot(fuseConfig),
        FuseProgressBarModule,
        FuseSharedModule,
        FuseSidebarModule,
        FuseThemeOptionsModule,

        // App modules
        LayoutModule
    ],
    bootstrap: [
        AppComponent
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: AppInterceptor, multi: true },
        { provide: MatPaginatorIntl, useValue: getSpanishPaginatorIntl() },
        { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig },
        AuthGuard
    ]
})
export class AppModule {
}