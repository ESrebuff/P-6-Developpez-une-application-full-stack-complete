import { Component, OnInit, Input } from '@angular/core';
import { Subject } from 'src/app/core/interfaces/subject.interface';
import { SubscriptionService } from 'src/app/core/services/subscription.service';

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.scss']
})
export class ThemeComponent implements OnInit {
  @Input() subject!: Subject;

  constructor(private subscriptionService: SubscriptionService) { }

  ngOnInit(): void {
  }

  onSubscribeClick(): void {
    if (this.subject.subscribed === false) {
      // Non abonné, abonnement
      this.subscriptionService.createSubscription(this.subject.id).subscribe(
        () => {;
          this.subject.subscribed = true;
        },
        (error) => {
          console.error('Erreur lors de l\'abonnement', error);
        }
      );
    } else {
      // Déjà abonné, désabonnement
      this.subscriptionService.deleteSubscription(this.subject.id).subscribe(
        () => {
          this.subject.subscribed = false;
        },
        (error) => {
          console.error('Erreur lors du désabonnement', error);
        }
      );
    }
  }
}
