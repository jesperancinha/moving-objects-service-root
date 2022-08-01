import {MetricsTag} from "./metrics.tag";
import {Statistic} from "./statistic";

export class Metrics {
    public measurements: Statistic[];
    public availableTags: MetricsTag[];
}
